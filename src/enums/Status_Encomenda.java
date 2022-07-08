package enums;

import java.util.HashMap;
import java.util.Map;

public enum Status_Encomenda {
	DISPONIVEL(1),
	EM_ANDAMENTO(2),
	FINALIZADA(3),
	CANCELADA(4);
	
	private int value;
    private static Map map = new HashMap<>();

	Status_Encomenda(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	
	static {
        for (Status_Encomenda statusEncomenda : Status_Encomenda.values()) {
            map.put(statusEncomenda.value, statusEncomenda);
        }
    }
	
	public static Status_Encomenda valueOf(int status) {
        return (Status_Encomenda) map.get(status);
    }
	
	public int getValue() {
		return value;
	}
}
