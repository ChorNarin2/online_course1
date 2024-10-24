package chornarin.com.kh.online_course.services.configservice;

import io.jsonwebtoken.Claims; 
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.SignatureAlgorithm; // For signing the JWT
import io.jsonwebtoken.io.Decoders; // For decoding the secret key
import io.jsonwebtoken.security.Keys; // For creating signing keys
import java.security.Key; // For the signing key type
import java.util.Date; // For handling date and time
import java.util.HashMap; // For creating a map of additional claims
import java.util.Map; // For additional claims in JWT
import java.util.function.Function; // For functional interfaces

import org.springframework.beans.factory.annotation.Value; // For injecting values from properties
import org.springframework.security.core.userdetails.UserDetails; // For user details handling
import org.springframework.stereotype.Service; 

@Service // Marks this class as a service component in Spring
public class JwtService {
    
    // Injects the JWT secret key from application properties
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    // Injects the JWT expiration time from application properties
    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    // Extracts the username (subject) from the provided JWT token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // Calls extractClaim to get the subject
    }

    // Generic method to extract specific claims from the JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token); // Retrieves all claims from the token
        return claimsResolver.apply(claims); // Applies the resolver function to the claims
    }

    // Generates a JWT token for the given user details without extra claims
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails); // Calls the overloaded method with empty extra claims
    }

    // Generates a JWT token with additional claims for the given user details
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration); // Builds and returns the token
    }

    // Returns the expiration time for the token
    public long getExpirationTime() {
        return jwtExpiration; // Returns the expiration time
    }

    // Constructs the JWT token with claims, user details, and expiration time
    private String buildToken(
            Map<String, Object> extraClaims, // Additional claims to include
            UserDetails userDetails, // User details for the token
            long expiration // Expiration time for the token
    ) {
        return Jwts // Uses the JJWT builder
                .builder() // Starts the token building process
                .setClaims(extraClaims) // Sets additional claims
                .setSubject(userDetails.getUsername()) // Sets the subject to the username
                .setIssuedAt(new Date(System.currentTimeMillis())) // Sets the issue date to current time
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Sets expiration date
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Signs the token with the secret key and HS256 algorithm
                .compact(); // Compacts the JWT into a string
    }

    // Validates the given JWT token against the provided user details
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Extracts the username from the token
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token); // Checks if the username matches and if the token is not expired
    }

    // Checks if the given JWT token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Compares expiration date with the current date
    }

    // Extracts the expiration date from the provided JWT token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration); // Calls extractClaim to get the expiration date
    }

    // Extracts all claims from the provided JWT token
    private Claims extractAllClaims(String token) {
        return Jwts // Uses the JJWT parser
                .parserBuilder() // Starts the parser builder
                .setSigningKey(getSignInKey()) // Sets the signing key for validation
                .build() // Builds the parser
                .parseClaimsJws(token) // Parses and validates the JWT
                .getBody(); // Returns the claims from the validated token
    }

    // Retrieves the signing key used for token generation and validation
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // Decodes the secret key from Base64
        return Keys.hmacShaKeyFor(keyBytes); // Creates and returns the HMAC signing key
    }
}