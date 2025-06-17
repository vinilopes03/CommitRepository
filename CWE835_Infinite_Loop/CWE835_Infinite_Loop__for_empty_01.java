/* FLAW: Infinite Loop - for() with no break point */
for (int i = 0; i < Integer.MAX_VALUE; i++) {
    // Do something
    System.out.println("Iteration: " + i);
}