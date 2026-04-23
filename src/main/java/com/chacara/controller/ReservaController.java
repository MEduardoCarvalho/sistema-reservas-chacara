package com.chacara.controller;

import com.chacara.model.Reserva;
import com.chacara.service.ReservaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @PostMapping
    public Reserva criar(@RequestBody Reserva reserva) {
        return service.criarReserva(reserva);
    }

    @GetMapping
    public List<Reserva> listar() {
        return service.listar();
    }

    @GetMapping("/faturamento/{mes}")
    public Double faturamento(@PathVariable int mes) {
        return service.faturamentoMensal(mes);
    }
}
