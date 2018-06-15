package org.extrahour.democompany.company;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

  private final CompanyRepo companyRepo;

  public CompanyService(CompanyRepo companyRepo) {
    this.companyRepo = companyRepo;
  }

  @Transactional
  public Company save(Company company) {
    return companyRepo.save(company);
  }

  @Transactional(readOnly = true)
  public Company get(long companyId) {
    return companyRepo.findById(companyId)
        .orElseThrow(() -> new IllegalArgumentException("Company does not exist: " + companyId));
  }

  @Transactional(readOnly = true)
  public List<Company> getAll() {
    List<Company> companies = new ArrayList<>();
    companyRepo.findAll().forEach(companies::add);
    return companies;
  }


}
