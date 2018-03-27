# Andorra-Space-Program
simulator of gravity in two dimensions between orbital bodys.
Uses a repeating method of a set interval to 
-determine forces between two bodies using newtons equations
-Use this force to determine the change in the objects velocity (using Newton 1)
-Move body appropriately (after all forces are resolved) and clear graphics to redraw all shapes.

All bodies are contained within an OBodies arrayList currently and must belong to OrbitalBody interface, providing the commands to move, apply forces etc. only body currently implemented is TestRock, basic black oval body.
main simulator currently contains option for force to be constantly applied downwards.

program may be more useable with a coordinate system that is more intuitive than using the top left side as the origin.
constants have not been tested yet and may need adjusting.
Main program not yet complete.
