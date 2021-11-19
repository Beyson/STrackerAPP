package com.example.ssis_tracker.repository.adjuntarimagenes;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import com.example.ssis_tracker.presenter.adjuntarimagenes.AdjuntarImagenesPresenter;
import java.util.ArrayList;

public class AdjuntarImagenesResposioryImpl implements AdjuntarImagenesRespository {

    private AdjuntarImagenesPresenter adjuntarImagenesPresenter;
    private Context context;

    public AdjuntarImagenesResposioryImpl(AdjuntarImagenesPresenter adjuntarImagenesPresenter , Context context){
        this.adjuntarImagenesPresenter = adjuntarImagenesPresenter;
        this.context = context;
    }

    @Override
    public void BuscarFolderImagenes() {

        ArrayList<String[]> FolderImagenes = new ArrayList<>();
        ArrayList<String>   FolderAgregado = new ArrayList<>();
        int cantidadImagenesFolder = 0;
        int cantidadTotalImagenes  = 0;
        Cursor  FolderImagenesProvider =  this.context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI                 ,
                new String[]{   MediaStore.Images.Media._ID                  ,
                                MediaStore.Images.Media.BUCKET_ID            ,
                                MediaStore.Images.Media.BUCKET_DISPLAY_NAME  ,
                                MediaStore.Images.Media.DATA
                }                                                            ,
                null                                                ,
                null                                             ,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME+" DESC , "+MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
        );

        if(FolderImagenesProvider.moveToFirst()){
            do{
                cantidadTotalImagenes++;
                if(!FolderAgregado.contains("Todas las Imagenes")){
                    FolderImagenes.add(new String[]{
                            FolderImagenesProvider.getString(FolderImagenesProvider.getColumnIndex(MediaStore.Images.Media.DATA)) ,
                            "Todas las Imagenes"  ,
                            "0"
                    });
                    FolderAgregado.add("Todas las Imagenes");
                }

                if(!FolderAgregado.contains(FolderImagenesProvider.getString(FolderImagenesProvider.getColumnIndex(MediaStore.Images.Media.BUCKET_ID)))){
                    if(cantidadImagenesFolder > 0){
                        FolderImagenes.get(FolderImagenes.size() -1)[2] = String.valueOf(cantidadImagenesFolder);
                    }
                    FolderImagenes.add(new String[]{
                            FolderImagenesProvider.getString(FolderImagenesProvider.getColumnIndex(MediaStore.Images.Media.DATA)),
                            FolderImagenesProvider.getString(FolderImagenesProvider.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))  ,
                            "0"
                    });
                    FolderAgregado.add(FolderImagenesProvider.getString(FolderImagenesProvider.getColumnIndex(MediaStore.Images.Media.BUCKET_ID)));
                    cantidadImagenesFolder = 1;
                }else{
                    cantidadImagenesFolder = cantidadImagenesFolder + 1;
                }
            }while(FolderImagenesProvider.moveToNext());
        }
        FolderImagenes.get(FolderImagenes.size()-1)[2]=String.valueOf(cantidadImagenesFolder);
        FolderImagenes.get(0)[2]=String.valueOf(cantidadTotalImagenes);

        this.adjuntarImagenesPresenter.MostarFolderImagenes(FolderImagenes);
    }

    @Override
    public void ImagenesEnCarpeta(String NombreFolder){

        ArrayList<String[]> Imagenes = new ArrayList<>();

        Cursor imagenprovider = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI        ,
                new String[]{
                        MediaStore.Images.Media._ID                 ,
                        MediaStore.Images.Media.BUCKET_ID           ,
                        MediaStore.Images.Media.BUCKET_DISPLAY_NAME ,
                        MediaStore.Images.Media.DATA
                } ,
                MediaStore.Images.Media.DATA+" LIKE ?" ,
                new String[]{"%"+NombreFolder+"%"},
                MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
        );

        if(imagenprovider.moveToFirst()){
            do{
                Imagenes.add(new String[]{
                        imagenprovider.getString(imagenprovider.getColumnIndex(MediaStore.Images.Media.DATA))                 ,
                        imagenprovider.getString(imagenprovider.getColumnIndex(MediaStore.Images.Media._ID))                  ,
                        imagenprovider.getString(imagenprovider.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))  ,
                        "0"
                });
            }while(imagenprovider.moveToNext());
        }

        this.adjuntarImagenesPresenter.MostarImagenes(Imagenes);

    }
}
