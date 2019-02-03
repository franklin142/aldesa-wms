package org.aldesa.wms.data;

import java.util.List;

import org.aldesa.wms.model.Bulk;
import org.aldesa.wms.model.BulkPendAutorizar;
import org.aldesa.wms.model.Deposito;
import org.aldesa.wms.model.MIEDESC_v;
import org.aldesa.wms.model.MercPendRecibir;
import org.aldesa.wms.model.MercRecibidaCompleta;
import org.aldesa.wms.model.MercadRecibida;
import org.aldesa.wms.model.MercadRecibidaPK;

public interface MercadRecibidaDao {
	public MercadRecibida getById(MercadRecibidaPK pk);
	public List<MercadRecibida> getAll();
	public List<MercRecibidaCompleta> getAllFull();
    public List<MercRecibidaCompleta> getAllFull(String dep);
	public void save(MercadRecibida dmr);
	public void update(MercadRecibida dmr);
	public Deposito getDepositoById(String id_deposito);
	public MercadRecibida byBulk(String bulk);
	public MIEDESC_v saveMie(MIEDESC_v miev);
	public Bulk saveBulk(Bulk bulk);
	public Bulk saveBulkDesc(Bulk bulk);
	public List<MercPendRecibir> getMercaderiaPendiente(String bulk);
	public List<BulkPendAutorizar> getAllBulkPendientes();
}
