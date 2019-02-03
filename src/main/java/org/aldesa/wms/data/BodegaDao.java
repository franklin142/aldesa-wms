package org.aldesa.wms.data;

import java.util.List;

import org.aldesa.wms.model.Bodega;
import org.aldesa.wms.model.EstadoMercancia;
import org.aldesa.wms.model.Muelle;

public interface BodegaDao {
	public Bodega getById(String pk);
	public List<Bodega> getAll();
	public List<Muelle> getAllMuelle();
	public List<EstadoMercancia> getAllEstadosMercancia();
	public void save(Bodega dmr);
	public Bodega byBulk(String bulk);
}
