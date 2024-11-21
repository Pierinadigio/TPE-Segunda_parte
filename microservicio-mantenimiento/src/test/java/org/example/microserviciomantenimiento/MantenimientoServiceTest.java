package org.example.microserviciomantenimiento;

import org.example.microserviciomantenimiento.entity.Mantenimiento;
import org.example.microserviciomantenimiento.repository.MantenimientoRepository;
import org.example.microserviciomantenimiento.service.MantenimientoMapper;
import org.example.microserviciomantenimiento.service.MantenimientoService;
import org.example.shareddto.DTO.entity.MantenimientoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class MantenimientoServiceTest {

    @Mock
    private MantenimientoRepository mantenimientoRepository;

    @Mock
    private MantenimientoMapper mantenimientoMapper;

    @InjectMocks
    private MantenimientoService mantenimientoService;

    @BeforeEach
    void setUp() {
        // Inicializar los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerMantenimientoPorId() {
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setId(1L);
        mantenimiento.setDescripcion("Cambio de batería");

        when(mantenimientoRepository.findById(1L)).thenReturn(Optional.of(mantenimiento));

        MantenimientoDTO mantenimientoDTO = new MantenimientoDTO();
        mantenimientoDTO.setId(1L);
        mantenimientoDTO.setDescripcion("Cambio de batería");
        when(mantenimientoMapper.toDTO(mantenimiento)).thenReturn(mantenimientoDTO);

        MantenimientoDTO resultado = mantenimientoService.obtenerMantenimientoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Cambio de batería", resultado.getDescripcion());
    }
}
