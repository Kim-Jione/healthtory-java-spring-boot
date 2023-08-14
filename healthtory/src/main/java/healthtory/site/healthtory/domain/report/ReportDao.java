package healthtory.site.healthtory.domain.report;

import java.util.List;

import healthtory.site.healthtory.web.dto.response.report.ReportRespDto;

public interface  ReportDao {
              
    public Report findById(Integer reportId);

	public List<Report> findAll();

	public void insert(Report report);

	public void update(Report report);

	public void delete(Integer reportId);

    public ReportRespDto findByReport();
}
