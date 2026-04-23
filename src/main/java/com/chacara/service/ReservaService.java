package com.chacara.service;

import com.chacara.model.Reserva;
import com.chacara.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public Reserva criarReserva(Reserva reserva) {

        boolean conflito = repository
            .existsByDataInicioLessThanEqualAndDataFimGreaterThanEqual(
                reserva.getDataFim(),
                reserva.getDataInicio()
            );

        if (conflito) {
            throw new RuntimeException("Já existe reserva nesse período!");
        }

        return repository.save(reserva);
    }

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Double faturamentoMensal(int mes) {
        return repository.findAll().stream()
            .filter(r -> r.getDataInicio().getMonthValue() == mes)
            .mapToDouble(Reserva::getValor)
            .sum();
    }
}
