package uk.ac.greenwich.aa5119a.demotimebank.util;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;

//@Service
public class JwtUtil {
//
//    private String SECRET_KEY = "ahmad_timeshare_secret";
//
//    public String extractEmail(String token){
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token){
//        return extractClaim(token,Claims::getExpiration);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//    }
//
//    private Boolean isTokenExpired(String token){
//        return extractExpiration(token).before(new Date());
//    }
//
//    public String generateToken(UserDetails userDetails){
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, userDetails.getUsername());
//    }
//
//    private String createToken(Map<String, Object> claims, String email) {
//        return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails){
//        final String email = extractEmail(token);
//        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
}
