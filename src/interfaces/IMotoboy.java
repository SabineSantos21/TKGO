package interfaces;

import java.sql.ResultSet;

import classes.Motoboy;

public interface IMotoboy {
	public Motoboy createMotoboy(Motoboy novoMotoboy);
	public ResultSet getMotoboys();
	public Motoboy getMotoboy(String idMotoboy);
	public Motoboy updateMotoboy(String idMotoboy, Motoboy motoboy);
	public void deleteMotoboy(String idMotoboy);
	public Motoboy getMotoboyByNome(String nome);
}
