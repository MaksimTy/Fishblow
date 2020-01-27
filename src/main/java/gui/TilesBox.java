package gui;

import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import model.Aquarium;
import model.Layer;
import model.Tile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class TilesBox extends ScrollPane {

    private Aquarium aquarium;
    private TilePane tilePane;
    private Image brick = new Image(Files.newInputStream(Paths.get("src/main/resources/" + Tile.Brick.name() + ".png")));


    public TilesBox(Aquarium aquarium) throws IOException {
        this.setContent(this.tilePane);
        this.setPrefWidth(Double.MAX_VALUE);
        this.setPrefHeight(Double.MAX_VALUE);
        this.setAquarium(aquarium);
    }

    private void fillAquarium(int width) throws IOException {
        if (this.aquarium == null) {
            for (int i = 0; i < width * width; i++) {
                this.tilePane.getChildren().add(new ImageView(brick));
            }
        } else {
            for (Layer layer : this.aquarium.getLayers()) {
                for (int j = 0; j < layer.getSize(); j++) {
                    this.tilePane.getChildren().add(new ImageView(
                            new Image(Files.newInputStream(
                                    Paths.get("src/main/resources/" + layer.getLayer()[j].name() + ".png")))));
                }
            }
        }
    }

    public void setAquarium(Aquarium aquarium) throws IOException {

        int width = 30;
        if (aquarium != null) {
            this.aquarium = aquarium;
            width = this.aquarium.getWidth();
        }
        this.tilePane = new TilePane();
        this.tilePane.setOrientation(Orientation.HORIZONTAL);
        this.tilePane.setPrefColumns(width);
        this.setContent(this.tilePane);
        this.fillAquarium(width);
    }
}
