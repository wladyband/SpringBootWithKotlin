package com.wladimir.bakery.BakeryOurDream.repository

import com.wladimir.bakery.BakeryOurDream.model.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository : JpaRepository<Categoria, Long?>{}