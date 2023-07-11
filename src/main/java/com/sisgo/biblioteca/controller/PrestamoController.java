package com.sisgo.biblioteca.controller;

import com.sisgo.biblioteca.objetos.Prestamo;
import com.sisgo.biblioteca.servicios.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    // CRUD - GET
    @GetMapping("/formPrestamo")
    public String formularioPrestamos(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        return "formPrestamo";
    }

    @GetMapping("/listPrestamo")
    public String listaPrestamo(Model model) {
        List<Prestamo> prestamos = prestamoService.obtenerPrestamos();
        model.addAttribute("prestamos", prestamos);
        return "listPrestamo";
    }

    // CRUD - POST
    @PostMapping("/formPrestamo")
    public String crearPrestamo(Prestamo prestamo) {
        prestamoService.guardarPrestamo(prestamo);
        return "redirect:/listPrestamo";
    }

    // CRUD - UPDATE
    @GetMapping("/editPrestamo/{id}")
    public String editarPrestamo(@PathVariable int id, Model model) {
        Optional<Prestamo> prestamo = prestamoService.actualizarPrestamoPorId(id);
        model.addAttribute("prestamo", prestamo.orElse(new Prestamo()));
        return "formPrestamo";
    }

    // CRUD - DELETE
    @GetMapping("/deletePrestamo/{id}")
    public String eliminarPrestamo(@PathVariable int id) {
        prestamoService.eliminarPrestamoPorId(id);
        return "redirect:/listPrestamo";
    }

}
