@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn (name = "ville_id")
private Ville ville ; 