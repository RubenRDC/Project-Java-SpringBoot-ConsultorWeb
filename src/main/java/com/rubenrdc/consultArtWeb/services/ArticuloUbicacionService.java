package com.rubenrdc.consultArtWeb.services;

import com.rubenrdc.consultArtWeb.Dao.IArticuloDao;
import com.rubenrdc.consultArtWeb.Dao.IUbicacionDao;
import com.rubenrdc.consultArtWeb.models.Articulo;
import com.rubenrdc.consultArtWeb.models.ArticuloDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ruben
 */
@Service
public class ArticuloUbicacionService {

    @Autowired
    private IArticuloDao artDao;
    @Autowired
    private IUbicacionDao ubicDao;

    public ArticuloDTO findArticuloDTOCompleteByIdOrCode(String x) {
        ArticuloDTO find = util(x);
        if (find != null) {
            find.setListCantsFromUbics(ubicDao.findUbicacionDTOByCodeArticulo(find.getCodigo()));
        }
        return find;
    }

    public List<ArticuloDTO> findAllArticulosDTO() {
        return artDao.findAllSimple();
    }

    public List<ArticuloDTO> findArticuloDTOLikeDesc(String cod, String Desc) {
        return artDao.findAllSimpleLikeDescripcion(cod, Desc);
    }

    public ArticuloDTO util(String x) {
        try {
            int v = Integer.parseInt(x);
            return artDao.findArticuloDTOById(v);
        } catch (NumberFormatException e) {
            return artDao.findArticuloDTOByCodigo(x);
        }
        /*switch (x) {//Java 17+ intanceof implicito en el switch!!
            case String code ->
                find = artDao.findArticuloDTOCompleteByIdOrCode(code);
            case Integer id ->
                find = artDao.findArticuloDTOCompleteByIdOrCode(id);
            default -> {
            }
        }*/
    }

    public Articulo saveArticulo(Articulo articulo) {
        return artDao.save(articulo);
    }

    public Articulo findById(int id) {
        Optional<Articulo> findById = artDao.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        }
        return null;
    }

    public boolean deleteById(int id) {
        Optional<Articulo> findById = artDao.findById(id);
        if (findById.isPresent()) {
            artDao.deleteById(id);
            return true;
        }
        return false;
    }
}
