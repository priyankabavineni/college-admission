package cfg.clg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cfg.clg.dto.ProgramDto;
import cfg.clg.entity.ProgramEntity;
import cfg.clg.exception.InvalidNameException;
import cfg.clg.repository.ProgramRepository;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepo;

    public ProgramDto saveProgram(ProgramDto dto) {
        if (dto.getProgram() == null || dto.getProgram().length() <3) {
            throw new InvalidNameException("Program name must be at least 3 characters.");
        }

        ProgramEntity entity = new ProgramEntity();
        BeanUtils.copyProperties(dto, entity);
        entity = programRepo.save(entity);

        ProgramDto out = new ProgramDto();
        BeanUtils.copyProperties(entity, out);
        return out;
    }


    public List<ProgramDto> getAllPrograms() {
        List<ProgramEntity> entities = programRepo.findAll();
        List<ProgramDto> dtos = new ArrayList<>();
        for (ProgramEntity p : entities) {
        	ProgramDto dto = new ProgramDto();
            BeanUtils.copyProperties(p, dto);
            dtos.add(dto);
        }
        return dtos;
    }
}