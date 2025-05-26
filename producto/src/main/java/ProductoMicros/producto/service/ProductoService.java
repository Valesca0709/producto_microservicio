package ProductoMicros.producto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProductoMicros.producto.model.Producto;
import ProductoMicros.producto.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    
    // Listar todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    
    // Buscar producto por ID
    public Optional<Producto> findById(int id) {
        return productoRepository.findById(id);
    }
    
    // Crear o actualizar un producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
    
    // Eliminar un producto
    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }
    
    // Actualizar solo el stock
    public Producto updateStock(int id, int nuevoStock) {
    Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    producto.setStock(nuevoStock);
    return productoRepository.save(producto);
     }
}
