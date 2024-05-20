package dev.patika.vet.api;

import dev.patika.vet.business.abstracts.IVeterinarianService;
import dev.patika.vet.core.result.Result;
import dev.patika.vet.core.result.ResultData;
import dev.patika.vet.core.utilities.ResultHelper;
import dev.patika.vet.dto.request.veterinarian.VeterinarianSaveRequest;
import dev.patika.vet.dto.request.veterinarian.VeterinarianUpdateRequest;
import dev.patika.vet.dto.response.CursorResponse;
import dev.patika.vet.dto.response.VeterinarianResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/veterinarians")
public class VeterinarianController {
    private final IVeterinarianService veterinarianService;

    public VeterinarianController(IVeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VeterinarianResponse> save(@Valid @RequestBody VeterinarianSaveRequest veterinarianSaveRequest) {
        return ResultHelper.created(this.veterinarianService.save(veterinarianSaveRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VeterinarianResponse> getById(@PathVariable("id") long id) {
        return ResultHelper.success(this.veterinarianService.getById(id));
    }

    @GetMapping("/date/{date}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VeterinarianResponse>> getByAvailableDate(
            @PathVariable("date") LocalDate date,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.veterinarianService.getByAvailableDate(date, page, pageSize));
    }

    @GetMapping("/{name}/")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VeterinarianResponse>> getByName(
            @PathVariable("name") String name,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.veterinarianService.getByName(name, page, pageSize));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VeterinarianResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.veterinarianService.cursor(page, pageSize));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VeterinarianResponse> update(@Valid @RequestBody VeterinarianUpdateRequest veterinarianUpdateRequest) {
        return ResultHelper.success(this.veterinarianService.update(veterinarianUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.veterinarianService.delete(id);
        return ResultHelper.ok();
    }
}