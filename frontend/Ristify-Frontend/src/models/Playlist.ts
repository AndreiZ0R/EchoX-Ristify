import {User} from "./User.ts";
import {Model} from "./Response.ts";

export interface Playlist extends Model {
    playlistId: bigint,
    user: User,
    name: string
}

export interface PlaylistSong extends Model {
    playlistId: bigint,
    song: Song
}

export interface PlaylistWithSongs extends Model {
    playlistId: bigint,
    name: string,
    songs: Song[]
}

export interface Song extends Model {
    songId: bigint,
    songName: string,
    artistName: string,
    albumName: string,
    url: string
}