package org.pentaho.platform.dataaccess.datasource;

import java.util.ArrayList;
import java.util.List;

import org.pentaho.platform.dataaccess.datasource.wizard.service.IXulAsyncDatasourceServiceManager;
import org.pentaho.ui.xul.XulServiceCallback;

public class MetadataUIDatasourceService implements IUIDatasourceAdminService{
  
  public static final String TYPE = "Metadata";
  private boolean editable = false;
  private boolean removable = true;
  private boolean importable = true;
  private boolean exportable = true;
  private String newUI = "builtin:";
  private String editUI = "builtin:";
  private IXulAsyncDatasourceServiceManager datasourceService;

  public MetadataUIDatasourceService(IXulAsyncDatasourceServiceManager datasourceService) {
    this.datasourceService = datasourceService;
  }
  @Override
  public String getType() {
    return TYPE;
  }

  @Override
  public void getIds(final XulServiceCallback<List<IDatasourceInfo>> callback) {
    datasourceService.getMetadataDatasourceIds(new XulServiceCallback<List<String>>() {

      @Override
      public void success(List<String> ids) {
        List<IDatasourceInfo> datasourceInfos = new ArrayList<IDatasourceInfo>();
        for(String id:ids) {
          if(id != null && id.length() > 0) {
            datasourceInfos.add(new DatasourceInfo(id, id, TYPE, editable, removable, importable, exportable));            
          }
        }
        callback.success(datasourceInfos);
      }

      @Override
      public void error(String message, Throwable error) {
        callback.error(message, error);
      }
    });
  }

  @Override
  public String getNewUI() {
    return newUI;
  }

  @Override
  public String getEditUI() {
    return editUI;
  }

}
