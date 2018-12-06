## Niveau de sécurité

 Lors du partage d'objets entre plusieurs threads, un certain niveau de sécurité est obtenu parmis les suivants:

	* Immutable
	* Unconditionally Thread-safe
	* Conditionally Thread-safe
	* Not Thread-safe
	* Thread-hostile

### Immutable

Le niveau immutable est obtenu quand l'état de l'objet ne change jamais une fois qu'il est crée. Toutes les méthodes de ces objets cont à chaque fois créer une nouvelle instance. Dans la librairie standard Java standard on retrouve la classe String, les wrappers, les types primitifs, la classe BigInteger, ...


### Objet Thread-safe

Les objets thread-safe sont mutables, néammoins, la classe utilise le mécanisme de synchronisation dans son implémentation interne et garantit des lors que son état sera toujours correct lorsque cet objet est partagé entre plusieurs threads.

Avec cette garantie, on peut donc utiliser de tels objets sans aucune précaution particulière. On retrouve notamment les classes Random et ConcurrentHashMap.


### Objet Thread-safe sous condition

Certaines des méthodes nécessitent d'utiliser de la synchronization externe afin de pouvoir les utiliser entre plusieurs threads.


### Objet non Thread-safe

Pour les utiliser à partir de plusieurs threads, il faut obligatoirement utiliser la synchronization externe


### Object hostile au thread

Ce sont des objets qui ne peuvent jamais être partagés entre plusieurs threads