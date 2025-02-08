package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    AuthorMapper authorMapper;
    @Autowired
    AuthorRepository authorRepository;

    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream().map(authorMapper::map).toList();
    }

    public AuthorDTO getById(@Valid long id) {
        return authorMapper.map(authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("")));
    }

    public AuthorDTO create(@Valid AuthorCreateDTO authorCreateDTO) {
        return authorMapper.map(authorRepository.save(authorMapper.map(authorCreateDTO)));
    }

    public AuthorDTO update(@Valid long id, @Valid AuthorUpdateDTO authorUpdateDTO) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        authorMapper.update(authorUpdateDTO, author);
        return authorMapper.map(authorRepository.save(author));
    }

    public void delete(@Valid long id) {
        authorRepository.deleteById(id);
    }
    // END
}
