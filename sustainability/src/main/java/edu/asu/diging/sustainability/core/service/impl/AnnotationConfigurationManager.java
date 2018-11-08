package edu.asu.diging.sustainability.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.diging.sustainability.core.data.AnnotationConfigurationRepository;
import edu.asu.diging.sustainability.core.data.ConceptRepository;
import edu.asu.diging.sustainability.core.model.config.IAnnotationConfiguration;
import edu.asu.diging.sustainability.core.model.config.impl.AnnotationConfiguration;
import edu.asu.diging.sustainability.core.model.impl.Concept;
import edu.asu.diging.sustainability.core.service.IAnnotationConfigurationManager;
import edu.asu.diging.sustainability.core.service.IConceptManager;
import edu.asu.diging.sustainability.web.admin.pages.AnnotationConfig;

@Service
@Transactional
public class AnnotationConfigurationManager implements IAnnotationConfigurationManager {

    @Autowired
    private AnnotationConfigurationRepository repo;
    
    @Autowired
    private ConceptRepository conceptRepo;
    
    /* (non-Javadoc)
     * @see edu.asu.diging.sustainability.core.service.impl.IAnnotationConfigurationManager#findAll()
     */
    @Override
    public List<IAnnotationConfiguration> findAll() {
        List<IAnnotationConfiguration> configs = new ArrayList<>();
        repo.findAll().forEach(c -> configs.add(c));
        return configs;
    }
    
    @Override
    public void storeConfigurations(Collection<AnnotationConfig> configs) {
        for (AnnotationConfig config : configs) {
            if (config.getId() != null) {
                Optional<AnnotationConfiguration> optional = repo.findById(config.getId());
                if (optional.isPresent()) {
                    AnnotationConfiguration configuration = optional.get();
                    setValues(configuration, config);
                    repo.save(configuration);
                    continue;
                }
            }
            Optional<Concept> conceptOptional = conceptRepo.findById(config.getConceptId());
            if (!conceptOptional.isPresent()) {
                // this should never happen, but if it does, just ignore it
                continue;
            }
            AnnotationConfiguration configuration = new AnnotationConfiguration(conceptOptional.get());
            setValues(configuration, config);
            repo.save(configuration);
        };
    }
    
    private void setValues(AnnotationConfiguration configuration, AnnotationConfig config) {
        configuration.setDisplayType(config.getDisplayType());
        configuration.setSection(config.getSection());
        configuration.setSortOrder(config.getSortOrder());
    }
}
