package com.kkwriter.gallery.service.product.impl;

import com.kkwriter.gallery.entity.product.GlyProductType;
import com.kkwriter.gallery.repository.product.GlyProductTypeRepository;
import com.kkwriter.gallery.service.product.IGlyProductTypeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lisha on 2018/6/8 18:42.
 *
 * @author lisha
 */
@Service(value = "glyProductTypeService")
public class GlyProductTypeServiceImpl implements IGlyProductTypeService {

    @Resource(name = "glyProductTypeRepository")
    private GlyProductTypeRepository repository;

    @Override
    public List<GlyProductType> findAll() {
        return repository.findAll(new Sort(Sort.Direction.DESC, "creationTime"));
    }

    @Override
    public void save(GlyProductType type) {
        repository.saveAndFlush(type);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
