package dev.patika.vet.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public interface IModelMapperService {
    ModelMapper forRequest();

    ModelMapper forResponse();
}