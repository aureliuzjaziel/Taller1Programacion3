package com.taller1Programacion.Controlador;

import com.taller1Programacion.Entidad.Paquete;
import com.taller1Programacion.Servicio.PaqueteServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/paquetes")
public class PaqueteControlador {
    private final PaqueteServicio paqueteServicio;

    // Cambia esta ruta si quieres que las imágenes se guarden en otro lugar
    private static final String UPLOAD_DIR = "uploads/paquetes/";

    public PaqueteControlador(PaqueteServicio paqueteServicio) {
        this.paqueteServicio = paqueteServicio;
    }

    @GetMapping
    public String listarPaquetes(Model model) {
        model.addAttribute("paquetes", paqueteServicio.listarTodos());
        return "pages/listaPaquetes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("paquete", new Paquete());
        return "pages/formPaquete";
    }

    @PostMapping("/guardar")
    public String guardarPaquete(@ModelAttribute Paquete paquete,
                                 @RequestParam(value = "imagenArchivo", required = false) MultipartFile imagenArchivo) throws IOException {
        if (imagenArchivo != null && !imagenArchivo.isEmpty()) {
            // Construir ruta absoluta
            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "paquetes";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String nombreArchivo = System.currentTimeMillis() + "_" + imagenArchivo.getOriginalFilename();
            File rutaDestino = new File(dir, nombreArchivo);
            imagenArchivo.transferTo(rutaDestino);

            // Guarda SOLO la ruta relativa para usar como src en HTML
            paquete.setRutaImagen("/uploads/paquetes/" + nombreArchivo);
        }
        paqueteServicio.guardar(paquete);
        return "redirect:/paquetes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Paquete paquete = paqueteServicio.buscarPorId(id);
        if (paquete == null) {
            return "redirect:/paquetes";
        }
        model.addAttribute("paquete", paquete);
        return "pages/formPaquete";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPaquete(@PathVariable Long id) {
        paqueteServicio.eliminarPorId(id);
        return "redirect:/paquetes";
    }
}