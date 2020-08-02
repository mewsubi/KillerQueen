package io.mewsub.killerqueen.entities;

import io.mewsub.killerqueen.KillerQueen;

import java.util.UUID;

import org.bukkit.entity.Entity;

import org.bukkit.metadata.FixedMetadataValue;

public class Bomb {

	private boolean detonating;

	private Entity ent;

	private UUID uuid;

	public Bomb( Entity ent ) {
		this.ent = ent;
		this.uuid = UUID.randomUUID();
		this.ent.setMetadata( this.uuid.toString(), new FixedMetadataValue( KillerQueen.plugin, "isYes" ) );
		this.ent.setMetadata( "bomb", new FixedMetadataValue( KillerQueen.plugin, "isYes" ) );
		this.detonating = false;
	}

	public Entity getEntity() {
		return this.ent;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public void setEntity( Entity ent ) {
		this.ent.setGlowing( false );
		this.ent.removeMetadata( "bomb", KillerQueen.plugin );
		this.ent = ent;
		this.ent.setMetadata( this.uuid.toString(), new FixedMetadataValue( KillerQueen.plugin, "isYes" ) );
		this.ent.setMetadata( "bomb", new FixedMetadataValue( KillerQueen.plugin, "isYes" ) );
		this.ent.setGlowing( true );
	}

	public boolean getDetonating() {
		return this.detonating;
	}

	public void detonate() {
		this.detonating = true;
		this.ent.setGlowing( false );
	}

}