package ra.edu.upload.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferRequest {
    private String to;
    private String candidateName;
    private String jobTitle;
    private String companyName;
}
