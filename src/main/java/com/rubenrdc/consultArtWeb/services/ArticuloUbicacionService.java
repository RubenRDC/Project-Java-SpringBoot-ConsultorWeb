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

    public ArticuloDTO findArticuloDTOCompleteByIdOrCode(String x) {
        Articulo find = util(x);
        if (find != null) {
            find.setListCantsFromUbics(ubicDao.findUbicacionByCodeArticulo(find.getCodigo()));
            return new ArticuloDTO(find);
        }
        return null;
    }

    public List<ArticuloDTO> findAllArticulosDTO() {
        return artDao.findAllSimple();
    }

    public List<ArticuloDTO> findArticuloDTOLikeDesc(String cod, String Desc) {
        return artDao.findAllSimpleLikeDescripcion(cod, Desc);
    }

    public Articulo util(String x) {
        try {
            int v = Integer.parseInt(x);
            return artDao.findArticuloById(v);
        } catch (NumberFormatException e) {
            return artDao.findArticuloByCodigo(x);
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
}
