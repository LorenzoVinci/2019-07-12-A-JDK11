package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	SimpleWeightedGraph<Food, DefaultWeightedEdge> grafo;
	Map<Integer,Food> idMap;
	List<Food> cibi;
	List<Coppia> coppie;
	FoodDao dao;

	public Model() {
		this.grafo = new SimpleWeightedGraph<Food, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.dao = new FoodDao();
		this.cibi = new ArrayList<Food>();
		this.coppie = new ArrayList<Coppia>();
		this.idMap = new HashMap<Integer, Food>();
	}
	
	public void creaGrafo() {
		Graphs.addAllVertices(this.grafo, this.cibi);
		for(Food f1 : cibi) {
			for(Food f2 : cibi) {
				if(this.dao.getCoppie(f1, f2) != null)
					this.coppie.add(this.dao.getCoppie(f1, f2));
			}
		}
		for(Coppia c : this.coppie) {
			Graphs.addEdge(this.grafo, c.getF1(), c.getF2(), c.getPeso());
		}
	}
	
	public List<Food> getVicini(Food f){
		List<Coppia> vicini = new ArrayList<>();
		List<Food> result = new ArrayList<>();
//		vicini = Graphs.neighborListOf(this.grafo, f1);
		for(Coppia c  : this.coppie) {
			if(c.getF1().equals(f) || c.getF2().equals(f))
				vicini.add(c);
		}
		Collections.sort(vicini);
		for(Coppia c : vicini) {
			if(c.getF1().equals(f))
				result.add(c.getF2());
			if(c.getF2().equals(f))
				result.add(c.getF1());
			if(result.size() == 5)
				return result;
		}
		return result;
	}
	
	public List<Food> listPortionsPortate(int n){
		this.cibi = this.dao.listFoodPortate(n);
		return this.cibi;
	}

}
