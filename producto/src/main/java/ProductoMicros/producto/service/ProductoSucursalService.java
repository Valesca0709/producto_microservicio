package ProductoMicros.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ProductoMicros.producto.dto.SucursalDTO;
import ProductoMicros.producto.model.ProductoSucursal;
import ProductoMicros.producto.repository.ProductoSucursalRepository;

@Service
public class ProductoSucursalService {

    @Autowired
    private ProductoSucursalRepository productoSucursalRepository;

    @Autowired
    private RestTemplate restTemplate;

    // ¡Ojo! Asegúrate de que esta URL termina en /
    private final String SUCURSAL_URL = "http://localhost:8090/api/v1/sucursal/";

    public List<ProductoSucursal> findAll() {
        return productoSucursalRepository.findAll();
    }

    public List<ProductoSucursal> findByNombreAndId(String nombre, int idProducto) {
        return productoSucursalRepository.findByProductoNombreAndProductoIdProducto(nombre, idProducto);
    }

    public double calcularTotal(int idProductoSucursal, int cantidad) {
        ProductoSucursal productoSucursal = productoSucursalRepository.findByIdProductoSucursal(idProductoSucursal);

        if (productoSucursal == null) {
            throw new IllegalArgumentException("ProductoSucursal no encontrado con ID: " + idProductoSucursal);
        }

        // Aquí agregamos correctamente el ID a la URL
        int idSucursal = productoSucursal.getIdSucursal();
        String sucursalUrl = SUCURSAL_URL + idSucursal;

        SucursalDTO sucursal = restTemplate.getForObject(sucursalUrl, SucursalDTO.class);

        if (sucursal == null) {
            throw new IllegalStateException("Sucursal no encontrada con ID: " + idSucursal);
        }

        System.out.println("Sucursal consultada: " + sucursal.getNombre());

        return productoSucursal.getPrecio_unitario() * cantidad;
    }
}

