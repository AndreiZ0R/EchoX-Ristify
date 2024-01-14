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
    songId: bigint | number,
    songName: string,
    artistName: string,
    albumName: string,
    duration: number,
    imageUrl: string,
    url: string,
}

//TODO: get some work here, lastSong should be lastSongs, also have a list for next songs for better ux
export interface MediaController {
    currentSong: Song,
    lastSong: Song,
    nextSong: Song,
    songProgress: number,
    volume: number,
    onSliderChange: (value: number) => void,
    onVolumeChange: (value: number) => void,
    playing: boolean,
    onPause: () => void,
    onPlay: () => void,
    audioRef?: any,
}