package com.ingjuanmaya.forohub.controller;

import com.ingjuanmaya.forohub.domain.cursos.Curso.CursoRepository;
import com.ingjuanmaya.forohub.domain.topicos.*;
import com.ingjuanmaya.forohub.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepositoy topicoRepositoy;


    @GetMapping
    @Cacheable(value = "listaTopicos")
    public Page<TopicoDTO> listarTopicos(
            @RequestParam(required = false) String nombreCurso,
            @PageableDefault(sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable paginacion
    ) {
        Page<Topico> topicos;
        if (nombreCurso == null) {
            topicos = topicoRepositoy.findAll(paginacion);
        } else {
            topicos = topicoRepositoy.findByNombreCurso(nombreCurso, paginacion);
        }
        return TopicoDTO.convert(topicos);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> registrarTopico(@RequestBody @Valid RegistrarTopico registrarTopico,
                                                     UriComponentsBuilder uriComponentsBuilder
    ){
        Topico topico = registrarTopico.convert(cursoRepository, usuarioRepository);
        topicoRepositoy.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesTopicoDTO> detallesTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepositoy.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DetallesTopicoDTO(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> actualizarTopico(@PathVariable Long id,
                                                     @RequestBody @Valid ActualizarTopico actualizarTopico
    ) {
        Optional<Topico> posibleTopico = topicoRepositoy.findById(id);
        if (posibleTopico.isPresent()) {
            Topico topico = actualizarTopico.actualizar(id, topicoRepositoy);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepositoy.findById(id);
        if (topico.isPresent()) {
            topicoRepositoy.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
