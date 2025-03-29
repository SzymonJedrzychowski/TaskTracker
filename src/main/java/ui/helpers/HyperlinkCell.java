package ui.helpers;

import enums.HyperlinkCellFields;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import objects.Task;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class HyperlinkCell extends TableCell<Task, String> {
    private final Hyperlink hyperlink = new Hyperlink();

    public HyperlinkCell(HyperlinkCellFields field) {
        hyperlink.setOnAction(_ -> {
            Task task = getTableRow().getItem();

            String link = switch (field) {
                case TASK_LINK -> task.getTaskLink();
                case DEVELOPMENT_TASK_LINK -> task.getDevelopmentTaskLink();
            };

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(link));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    protected void updateItem(String link, boolean empty) {
        super.updateItem(link, empty);
        if (empty || link == null) {
            setGraphic(null);
        } else {
            String[] linkParts = link.split("/");
            hyperlink.setText(linkParts[linkParts.length - 1]);
            setGraphic(hyperlink);
        }
    }


}
