package com.rubenrdc.consultArtWeb.services;

import com.rubenrdc.consultArtWeb.Dao.IArticuloDao;
import com.rubenrdc.consultArtWeb.Dao.IUbicacionDao;
import com.rubenrdc.consultArtWeb.models.Articulo;
import com.rubenrdc.consultArtWeb.models.ArticuloDTO;
import java.util.List;
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

    public ArticuloDTO findArticuloDTOCompleteByCode(String code) {
        Articulo find = artDao.findArticuloByCodigo(code);
        if (find != null) {
            find.setListCantsFromUbics(ubicDao.findUbicacionByArticulo(code));
            return new ArticuloDTO(find);
        }
        return null;
    }

    public List<ArticuloDTO> findAllArticulosDTO() {
        return artDao.findAllSimple();
    }
}
