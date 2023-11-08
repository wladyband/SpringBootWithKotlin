package com.wladimir.bakery.BakeryOurDream.resource

import com.wladimir.bakery.BakeryOurDream.model.Categoria
import com.wladimir.bakery.BakeryOurDream.repository.CategoriaRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/categorias")
class CategoriaResource {

    @Autowired
    private lateinit var categoriaRepository: CategoriaRepository

    @GetMapping
    fun listar(): List<Categoria> {
        return categoriaRepository.findAll()
    }

    @PostMapping
    fun criar(@RequestBody categoria: Categoria, response: HttpServletResponse): ResponseEntity<Categoria> {
        val categoriaSalva = categoriaRepository.save(categoria)

        val uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{codigo}")
            .buildAndExpand(categoriaSalva.codigo)
            .toUri()

        response.setHeader("Location", uri.toASCIIString())

        return ResponseEntity.created(uri).body(categoriaSalva)
    }

    @GetMapping("/{codigo}")
    fun buscarPeloCodigo(@PathVariable codigo: Long): Categoria? {
        return categoriaRepository.findById(codigo).orElse(null)
    }
}