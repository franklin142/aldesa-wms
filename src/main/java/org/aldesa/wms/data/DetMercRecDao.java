package org.aldesa.wms.data;

import java.util.List;

import org.aldesa.wms.model.DetMercRec;
import org.aldesa.wms.model.DetMercRecPK;
import org.aldesa.wms.model.MercadRecibidaPK;

public interface DetMercRecDao {
	public DetMercRec getById(DetMercRecPK pk);
	public List<DetMercRec> getAll();
	public List<DetMercRec> getAll(MercadRecibidaPK merc_rec);
	public void save(DetMercRec dmr);
	public DetMercRec byBulk(String bulk);
}
