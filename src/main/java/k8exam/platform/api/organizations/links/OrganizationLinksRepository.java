package k8exam.platform.api.organizations.links;

import k8exam.platform.api.organizations.Organization;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationLinksRepository extends PagingAndSortingRepository<OrganizationLink, Long> {

    int deleteByChildAndParent(Organization child, Organization parent);

}
