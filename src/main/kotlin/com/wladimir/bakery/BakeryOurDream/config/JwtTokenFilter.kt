package com.wladimir.bakery.BakeryOurDream.config




import com.auth0.jwt.JWT
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import java.nio.charset.StandardCharsets

class JwtTokenFilter(private val jwtSecret: String) : OncePerRequestFilter() {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader
    constructor(jwtSecret: String, resourceLoader: ResourceLoader) : this(jwtSecret) {
        this.resourceLoader = resourceLoader
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = extractToken(request)
        if (token != null) {
            try {
                val audClaim = JWT.decode(token).getClaim("aud").asString()
                if (audClaim == getClientId()) {
                    val authentication = UsernamePasswordAuthenticationToken(null, null, emptyList())
                    SecurityContextHolder.getContext().authentication = authentication
                }
            } catch (e: Exception) {
                println(e)
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization")
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7)
        }
        return null
    }

    private fun getClientId(): String? {
        val resource: Resource = resourceLoader.getResource("classpath:google-oauth-client-id.txt")
        try {
            val clientIdBytes = resource.inputStream.readAllBytes()
            return String(clientIdBytes, StandardCharsets.UTF_8).trim()
        } catch (e: Exception) {
            // Lidar com erros de leitura do arquivo
            e.printStackTrace()
            return null
        }
    }
}
