## Niveau de s�curit�

 Lors du partage d'objets entre plusieurs threads, un certain niveau de s�curit� est obtenu parmis les suivants:

	* Immutable
	* Unconditionally Thread-safe
	* Conditionally Thread-safe
	* Not Thread-safe
	* Thread-hostile

### Immutable

Le niveau immutable est obtenu quand l'�tat de l'objet ne change jamais une fois qu'il est cr�e. Toutes les m�thodes de ces objets cont � chaque fois cr�er une nouvelle instance. Dans la librairie standard Java standard on retrouve la classe String, les wrappers, les types primitifs, la classe BigInteger, ...


### Objet Thread-safe

Les objets thread-safe sont mutables, n�ammoins, la classe utilise le m�canisme de synchronisation dans son impl�mentation interne et garantit des lors que son �tat sera toujours correct lorsque cet objet est partag� entre plusieurs threads.

Avec cette garantie, on peut donc utiliser de tels objets sans aucune pr�caution particuli�re. On retrouve notamment les classes Random et ConcurrentHashMap.


### Objet Thread-safe sous condition

Certaines des m�thodes n�cessitent d'utiliser de la synchronization externe afin de pouvoir les utiliser entre plusieurs threads.


### Objet non Thread-safe

Pour les utiliser � partir de plusieurs threads, il faut obligatoirement utiliser la synchronization externe


### Object hostile au thread

Ce sont des objets qui ne peuvent jamais �tre partag�s entre plusieurs threads