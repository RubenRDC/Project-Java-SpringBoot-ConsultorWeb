package com.rubenrdc.consultArtWeb.services;

import com.rubenrdc.consultArtWeb.Dao.IArticuloDao;
import com.rubenrdc.consultArtWeb.models.Articulo;
import com.rubenrdc.consultArtWeb.models.ArticuloDTO;
import com.rubenrdc.consultArtWeb.models.ArticuloUbicacionDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rubenrdc.consultArtWeb.Dao.IArticuloUbicacionDao;
import com.rubenrdc.consultArtWeb.Dao.IDepositoDao;
import com.rubenrdc.consultArtWeb.Dao.IUbicacionDao;
import com.rubenrdc.consultArtWeb.models.Deposito;
import com.rubenrdc.consultArtWeb.models.Ubicacion;
import java.util.Map;

/**
 *
 * @author Ruben
 */
@Service
public class ArticuloUbicacionService {

    @Autowired
    private IArticuloDao artDao;
    @Autowired
    private IArticuloUbicacionDao ArtUbicDao;
    @Autowired
    private IDepositoDao depDao;
    @Autowired
    private IUbicacionDao ubicDao;

    public ArticuloDTO findArticuloDTOCompleteByIdOrCode(String x) {
        ArticuloDTO find = util(x);
        if (find != null) {
            find.setListCantsFromUbics(ArtUbicDao.findUbicacionDTOByCodeArticulo(find.getCodigo()));
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

    public Articulo findArticuloById(int id) {
        Optional<Articulo> findById = artDao.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        }
        return null;
    }

    public Articulo findArticuloByCode(String code) {
        return artDao.findArticuloByCodigo(code);
    }

    public boolean deleteArticuloById(int id) {
        Optional<Articulo> findById = artDao.findById(id);
        if (findById.isPresent()) {
            artDao.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ArticuloUbicacionDTO> findArticuloUbicacionbyCodeArticulo(String codigo) {
        return ArtUbicDao.findUbicacionDTOByCodeArticulo(codigo);
    }

    public Map saveArticuloUbicacion(ArticuloUbicacionDTO artUbicDTO, String codeArt) {
        //Comprobar que el deposito exista
        Deposito depIfExist = depDao.findByDescrip(artUbicDTO.getDeposito());
        if (depIfExist != null) {
            //Comprobar q el articulo exista
            Articulo findArticuloByCodigo = artDao.findArticuloByCodigo(codeArt);
            if (findArticuloByCodigo != null) {
                //Comprobar si la ubicacion ya existe o no
                Ubicacion ubicIfExist = ubicDao.findByUbic((artUbicDTO.getUbicacion().toUpperCase()));
                if (ubicIfExist == null) {
                    ubicIfExist = ubicDao.save(new Ubicacion(artUbicDTO.getUbicacion()));
                }
                ArtUbicDao.addUbicacionInArticulo(findArticuloByCodigo.getId(), ubicIfExist.getId(), depIfExist.getId(), artUbicDTO.getStockInUbicacion());
                return Map.of();
            }
            return Map.of(codeArt, "Articulo Inexistente");
        }
        return Map.of(artUbicDTO.getDeposito(), "Deposito Inexistente");
    }
}
