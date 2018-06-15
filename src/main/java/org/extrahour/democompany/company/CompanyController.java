package org.extrahour.democompany.company;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("companies")
public class CompanyController {

  private final CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @PostMapping
  public Company create(@Valid @RequestBody Company company) {
    return companyService.save(company);
  }

  @PutMapping("{companyId}")
  public Company update(@PathVariable long companyId, @Valid @RequestBody Company company) {
    company.setId(companyId);
    return companyService.save(company);
  }

  @GetMapping("{companyId}")
  public Company get(@PathVariable long companyId) {
    return companyService.get(companyId);
  }

  @GetMapping
  public List<Company> getAll() {
    return companyService.getAll();
  }
}
