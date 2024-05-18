package dev.patika.vet.api;

import dev.patika.vet.business.abstracts.IAnimalService;
import dev.patika.vet.core.result.Result;
import dev.patika.vet.core.result.ResultData;
import dev.patika.vet.core.utilities.ResultHelper;
import dev.patika.vet.dto.request.animal.AnimalSaveRequest;
import dev.patika.vet.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vet.dto.response.AnimalResponse;
import dev.patika.vet.dto.response.CursorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final IAnimalService animalService;

    public AnimalController(IAnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return ResultHelper.created(this.animalService.save(animalSaveRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getById(@PathVariable("id") long id) {
        return ResultHelper.success(this.animalService.getById(id));
    }

    @GetMapping("/{name}/")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> getByName(
            @PathVariable("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.animalService.getByName(name, page, pageSize));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.animalService.cursor(page, pageSize));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        return ResultHelper.success(this.animalService.update(animalUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }
}
