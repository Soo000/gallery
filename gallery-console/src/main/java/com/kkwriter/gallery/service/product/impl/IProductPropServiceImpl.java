package com.kkwriter.gallery.service.product.impl;

import com.kkwriter.gallery.entity.product.GlyProductProp;
import com.kkwriter.gallery.repository.product.GlyProductPropRepository;
import com.kkwriter.gallery.service.product.IProductPropService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lisha on 2018/6/7 17:55.
 *
 * @author lisha
 */
@Service(value = "productPropService")
public class IProductPropServiceImpl implements IProductPropService {

    @Resource(name = "glyProductPropRepository")
    private GlyProductPropRepository repository;

    @Override
    public List<GlyProductProp> findAll() {
        return repository.findAll(new Sort(Sort.Direction.DESC, "creationTime"));
    }

    @Override
    public void save(GlyProductProp prop) {
        repository.saveAndFlush(prop);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
