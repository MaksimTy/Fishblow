package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.Aquarium;
import model.Tile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CanvasBox extends ScrollPane {

    private Aquarium aquarium;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Image brick = new Image(
            Files.newInputStream(
                    Paths.get("src/main/resources/" + Tile.Brick.name() + ".png")));
    private final double tile = 8;

    public CanvasBox(Aquarium aquarium) throws IOException {
        this.setContent(this.canvas);
        this.setPrefWidth(Double.MAX_VALUE);
        this.setPrefHeight(Double.MAX_VALUE);
        this.setAquarium(aquarium);
    }

    private void fillAquarium(int width) {
        if (this.aquarium == null) {
            this.aquarium = null;
            this.canvas.setWidth(width * brick.getWidth());
            this.canvas.setHeight(width / 4 * brick.getHeight());
            for (int i = 0; i < width / 4; i++) {
                for (int j = 0; j < width; j++) {
                    this.graphicsContext.drawImage(
                            brick, j * brick.getHeight(), i * brick.getWidth());
                }
            }
        } else {
            this.canvas.setWidth(this.aquarium.getWidth() * brick.getWidth());
            this.canvas.setHeight(this.aquarium.getHeight() * brick.getHeight());

            for (int i = 0; i < this.aquarium.getLayers().size(); i++) {
                for (int j = 0; j < aquarium.getLayers().get(i).getLayer().length; j++) {

                    this.graphicsContext.setFill(
                            Color.valueOf(
                                    this.aquarium.getLayers().get(i).getLayer()[j].getColor()));
                    this.graphicsContext.setStroke(Color.GRAY);
                    this.graphicsContext.fillRect(j * tile, i * tile, tile, tile);
                    this.graphicsContext.strokeRect(j * tile, i * tile, tile, tile);
                }
            }
        }
    }

    public void setAquarium(Aquarium aquarium) {

        int width = 140;
        if (aquarium != null) {
            this.aquarium = aquarium;
            width = this.aquarium.getWidth();
        }
        this.canvas = new Canvas();
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        this.setContent(this.canvas);
        this.fillAquarium(width);
    }
}
