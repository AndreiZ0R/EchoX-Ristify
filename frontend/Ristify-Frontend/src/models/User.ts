import {Model} from "./Response.ts";

export type UserRole = "User" | "Artist";

export interface User extends Model {
    userId: bigint,
    username: string,
    firstName: string,
    lastName: string,
    email: string,
    country: string,
    createdAt: Date,
    birthDate: Date,
    role: UserRole,

    isArist(role: UserRole): boolean
}

export interface Friendship extends Model {
    user1: User,
    user2: User,
    createdAt: Date
}

export interface LoginModel extends User {
    token: string
}