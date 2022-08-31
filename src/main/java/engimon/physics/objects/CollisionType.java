package engimon.physics.objects;

public enum CollisionType {
    /**
     * Does not collide at all with anything
     */
    NO_COLLISIONS,

    /**
     * Reacts with ONLY_INFLICT and ALL_COLLISIONS themself,
     * but does not cause other object to react
     * Should be used for very light objects, like particles
     */
    ONLY_REACT,

    /**
     * Does not react on collisions, but causes active
     * objects to react
     * Should be used for immovable objects, like ground and walls
     */
    ONLY_INFLICT,
    /**
     * Reacts both actively and passively to collisions with all
     * objects, except those marked with NO_COLLISIONS
     * Should be used for most physical objects, like crates, balls, player etc.
     */
    ALL_COLLISIONS;

    public boolean isReactive() {
        return this == ONLY_REACT || this == ALL_COLLISIONS;
    }

    public boolean isPassive() {
        return this == ONLY_INFLICT || this == ALL_COLLISIONS;
    }

}
