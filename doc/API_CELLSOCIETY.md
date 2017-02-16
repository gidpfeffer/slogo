
#API Critique
Examine one API from a previous Cell Society project and categorize each method from following perspectives:

* the method should not be part of the API (i.e., it should not be public)
None 
* the method should be part of the External API because it helps other people on the team write their code

from `Grid` class: 
```public abstract Map<Integer[], Unit> getNeighbors(int row, int col);
public Map<Integer[], Unit> getInstances(Unit U);
public Unit getUnit(int row, int col)
public void setUnit(int row, int col, Unit u)
public List<Unit> getChildren()
public List<List<Unit>> getGrid()
public Map<Integer[], Unit> getFiniteNeighbors(int row, int col) 
public Map<Integer[], Unit> getToroidalNeighbors(int row, int col)
```
from `GridGenerator` class:

	public Grid returnCurrGrid()
	public Grid returnNextGrid()
	public Grid returnInitialGrid()

from `Model` class; 

	public Group getRoot()
	public abstract int getUnitA();
	public abstract int getUnitB();

from `ReadXMLFile`  class:

	 public static Map<String, List<List<Character>>> returnMap() 

from `SimulationGUI`

	public SimulationGUI(Timeline t, ResourceBundle r) 
	public Button createResetBtn(Model model)
	public Button createPauseBtn()
	public Button createHomeBtn(Scene homeScene, Model model, Stage s)
					public void handle(KeyEvent code) 
	public Slider createSpeedSlider() 
	public LineChart<Number, Number> createGraph(Model model) 
	public void addData(Model model) 



*  the method should be part of the Internal API because it helps future coders extend this component

All methods from subclasses of `Model` and `Grid` classes

## Six API's

### Simulation

* Internal

The methods and classes associated with the Units can all be considered internal. The model and Grid should have access to these, but external classes beyond the above mentioned shouldn't be able to access the API.

* External 

The abstract Model class would be an external API. Users can make new models when they feel the need to and can access all of the public methods in the model. The front end can use updateGrid to move through the simulation and can reset the model by calling reset. 

### Configuration

* Internal

The internal APIs are the grid. The grid generator makes a grid, but others don't access the Grid class. The Grid class should not be make to stand on its own. It should be used to implement other features.

* External

The Grid generator should be used externally. to make grids when necessary. This also helps to make the Models. It can be extended to have multiple simulations running at the same time for example.

### Visualization

* Internal

The Simulation GUI and scene setup are both internal API's. They can be used by the main to help setup a scene to run simulations.

* External 

The main method had no public methods so when agreed that there is no applicable external API from a visualization stand point.