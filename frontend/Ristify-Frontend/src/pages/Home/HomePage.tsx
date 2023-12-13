import {User} from "../../models/User.ts";
import {CSSProperties} from "react";
import styles from "./HomePage.module.scss"
import {useCustomQuery, useLogin} from "../../hooks/CustomHooks.ts";
import {Queries} from "../../constants/constants.ts";
import {retrieveUsers} from "../../api/Api.ts";
import {Button} from '@chakra-ui/react'

export default function HomePage() {

    const mutation = useLogin();
    // const {data: users, error, isLoading, setEnabledQuery} = useFetchUsers(false);

    const {data: users, error, isLoading, setEnabledQuery} =
        useCustomQuery<User[]>(Queries.USERS, retrieveUsers, false);


    return (
        <>
            <div className={styles.special}>HomePage</div>
            <Button bgColor="primary.base" _hover={{backgroundColor: "primary.lighter"}} onClick={() => {
                const data = {username: "popaopa", password: "popaopa"};
                mutation.mutate(data, {
                    onSuccess: () => {
                        setEnabledQuery(true);
                    }
                });
            }}>
                Login
            </Button>

            {
                isLoading ?
                    <div>Loading users...</div> :
                    error ? <>Error fetching users...</> : users?.map((user: User) => <UserCard user={user}/>)
            }
        </>
    )
}

type UserProps = {
    user: User
}

function UserCard({user}: UserProps) {

    const inlineStyle: CSSProperties = {
        padding: "10px 12px",
        backgroundColor: "olive",
        borderRadius: "15px",
        marginTop: "10px",
        marginRight: "10px",
        display: "inline-flex",
        alignItems: "flex-start",
        justifyContent: "center",
        flexDirection: "column",
    }

    return (
        <>
            <div style={inlineStyle}>
                <span className={styles.userText}>{user.username}</span>
                <span>{user.email}</span>
            </div>
        </>
    )
}