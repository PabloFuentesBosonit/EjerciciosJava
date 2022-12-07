package com.example.facturaJPA.linea.application;

import com.example.facturaJPA.linea.controller.dto.LineaMapper;
import com.example.facturaJPA.linea.controller.dto.LineaInputDto;
import com.example.facturaJPA.linea.controller.dto.LineaOutputDto;
import com.example.facturaJPA.linea.domain.Linea;
import com.example.facturaJPA.linea.repository.LineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LineaServiceImpl implements LineaService {

    @Autowired
    private LineaRepository lineaRepository;


    @Override
    public LineaOutputDto addLinea(LineaInputDto lineaInput) {
        Linea linea = LineaMapper.Instance.lineaInputDtoToLinea(lineaInput);
        Linea lineaDb = lineaRepository.save(linea);
        return LineaMapper.Instance.lineaToLineaOutputDto(lineaDb);
    }

    @Override
    public LineaOutputDto getLineaById(int id) {
        Linea linea = lineaRepository.findById(id).orElseThrow();
        return LineaMapper.Instance.lineaToLineaOutputDto(linea);
    }

    @Override
    public void deleteLineaById(int id) {
        lineaRepository.findById(id).orElseThrow();
        lineaRepository.deleteById(id);
    }

    @Override
    public Iterable<LineaOutputDto> getAllLineas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return lineaRepository.findAll()
                .stream()
                .map(linea -> LineaMapper.Instance.lineaToLineaOutputDto(linea)).toList();
    }

    @Override
    public LineaOutputDto updateLinea(LineaInputDto lineaInputDto, int id) throws Exception {
        Linea lineaDb = lineaRepository.findById(id).orElseThrow();
        Linea linea = LineaMapper.Instance.lineaInputDtoToLinea(lineaInputDto);
        Boolean isEqual = Objects.equals(lineaDb, linea);
        if(isEqual){
            throw new Exception();
        }
        lineaRepository.save(linea);
        return LineaMapper.Instance.lineaToLineaOutputDto(linea);
    }
}
