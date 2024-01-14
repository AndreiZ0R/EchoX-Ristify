import "./HomePage.scss"
import Footer from "../../components/Footer/Footer.tsx";
import {Box, Button, Flex, Hide, Image, Input, InputGroup, InputLeftElement, Show, Spinner, Text} from "@chakra-ui/react";
import {useCustomQuery, useLogin, useMediaController} from "../../hooks/CustomHooks.ts";
import React, {ReactElement, useEffect, useState} from "react";
import {Playlist} from "../../models/Playlist.ts";
import {Queries} from "../../constants/constants.ts";
import {retrievePlaylists} from "../../api/Api.ts";
import {BiLibrary} from "react-icons/bi";
import {IoMdHome} from "react-icons/io";
import {FaArrowLeft, FaArrowRight, FaUser} from "react-icons/fa";
import AppIconButton from "../../components/AppIconButton.tsx";
import {LuPlus} from "react-icons/lu";
import {SearchIcon} from "@chakra-ui/icons";
import {RiPlayCircleFill} from "react-icons/ri";

enum PanelType { HOME, PROFILE, LIBRARY}

interface PanelsState {
    activePanel: PanelType,
    leftPanelExpanded: boolean
}

interface Filter {
    label: string,
    selected: boolean,
    onClick: () => void
}

//TODO: fetch playlists & songs & populate

export default function HomePage() {
    const {mediaController, onBack, onSkip} = useMediaController();
    const [panels, setPanels] = useState<PanelsState>({
        activePanel: PanelType.HOME,
        leftPanelExpanded: false
    });

    const filters: Filter[] = [
        {
            label: "Playlists", selected: true, onClick: () => {
            }
        },
        {
            label: "Podcasts & Shows", selected: false, onClick: () => {
            }
        },
        {
            label: "Albums", selected: false, onClick: () => {
            }
        },
        {
            label: "Artists", selected: false, onClick: () => {
            }
        },
        {
            label: "For You", selected: true, onClick: () => {
            }
        }
    ];
    const login = useLogin();
    const {data: playlists, isLoading, setEnabledQuery} =
        useCustomQuery<Playlist[]>(Queries.PLAYLISTS_WITH_SONGS, retrievePlaylists, false);

    const [shuffledPlaylists, setShuffledPlaylists] = useState<Playlist[]>([]);


    useEffect(() => {
        const data = {username: "popaopa", password: "popaopa"};
        login.mutate(data, {
            onSuccess: () => {
                setEnabledQuery(true);
            }
        });
    }, []);

    useEffect(() => {
        setShuffledPlaylists([...playlists ?? []].sort(() => 0.5 - Math.random()))
    }, [playlists]);

    const togglePanels = (panel: PanelType) => {
        setPanels({
            ...panels,
            activePanel: panel
        });
    }

    const toggleLeftPanel = () => {
        setPanels({
            ...panels,
            leftPanelExpanded: !panels.leftPanelExpanded
        })
    };

    // TODO: search + finish
    const homeView = (): ReactElement => (<>
        <Box w="full" h="full" bgGradient='linear(to-b, background.hover, background.base, background.base, background.base)' overflow="auto" p={3}
             sx={{
                 '::-webkit-scrollbar': {
                     display: 'none'
                 }
             }}>
            {/* search */}
            <InputGroup>
                <InputLeftElement pointerEvents='none'>
                    <SearchIcon color='primary.base'/>
                </InputLeftElement>
                <Input
                    type='text'
                    placeholder='Search'
                    focusBorderColor="primary.base"
                    borderColor="gray"
                    _placeholder={{color: "gray"}}
                    color="primary.lighter"
                />
            </InputGroup>

            {/* welcome text */}
            <Box mt={4} mb={4}>
                <Text color="background.light" fontSize="3rem" fontWeight="bold">Hello, <Text color="primary.base" display="inline">Andrei</Text></Text>
                <Text color="gray">Welcome back.</Text>
            </Box>

            <Flex flexWrap="wrap" w="full" mt={4} mb={4} gap={3}>
                {
                    shuffledPlaylists.slice(0, 4).map(val => <ShowcasedPlaylistCard playlist={val} key={val.playlistId}/>)}
            </Flex>


            <PlaylistSection label="Today's biggest hits" playlists={playlists}/>
            <PlaylistSection label="Recommended for you" playlists={playlists}/>
            <PlaylistSection label="For a romantic night" playlists={playlists}/>
        </Box>
    </>)
    const profileView = (): ReactElement => (<>
        <Text color="red">Profile</Text>
    </>)

    const libraryView = (): ReactElement => (<>
        <Text color="red">Library</Text>
    </>)

    const getActiveView = () => {
        switch (panels.activePanel) {
            case PanelType.HOME:
                return homeView();
            case PanelType.PROFILE:
                return profileView();
            case PanelType.LIBRARY:
                return libraryView();
        }
    }

    return (<>
        <Box w="100%" h="100vh" display="flex" alignItems="center" flexDirection="column">
            {/*<Flex h="full" w="full" bgColor="gray"  gap={2} overflow="auto">*/}
            {/*    <Box h="full" w="25%" bgColor="pink">b1</Box>*/}
            {/*    <Box bgColor="purple" overflow="auto" w="full">*/}
            {/*        <div>ok</div>*/}
            {/*        <div>ok</div>*/}
            {/*        <div>ok</div>*/}
            {/*    </Box>*/}
            {/*</Flex>*/}


            {/* layout */}
            <Flex w="full" h="full" bgColor="background.darker" flexDirection={{lg: "row", md: "row", sm: "column", base: "column"}} gap={2}
                  p={2}
                  overflow="auto">
                {/*left panel*/}
                <Box h={{lg: "full", md: "full", sm: "10%", base: "10%"}}
                     w={{
                         lg: panels.leftPanelExpanded ? "50%" : "25%",
                         md: panels.leftPanelExpanded ? "50%" : "25%",
                         sm: "full",
                         base: "full"
                     }}
                     overflowX="hidden"
                     overflowY="auto"
                     minW="22%"
                     bgColor="transparent"
                     gap={2}
                     display="flex"
                     flexDirection={{lg: "column", md: "column", sm: "row", base: "row"}}
                     transition="width 150ms linear">

                    {/*up*/}
                    <Box h={{lg: "15%", md: "15%", sm: "full", base: "full"}} w="full" bgColor="background.base" rounded="lg" display="flex"
                         flexDirection={{lg: "column", md: "column", sm: "row", base: "row"}} alignItems="center">
                        <PanelEntry label="Home" icon={<IoMdHome/>} isActive={panels.activePanel === PanelType.HOME}
                                    onClick={() => togglePanels(PanelType.HOME)}/>
                        <PanelEntry label="Profile" icon={<FaUser/>} isActive={panels.activePanel === PanelType.PROFILE}
                                    onClick={() => togglePanels(PanelType.PROFILE)}/>
                        <Show below="md">
                            <PanelEntry
                                label="Library"
                                icon={<BiLibrary/>}
                                isActive={panels.activePanel === PanelType.LIBRARY}
                                onClick={() => togglePanels(PanelType.LIBRARY)}/>
                        </Show>
                    </Box>

                    {/*down*/}
                    <Hide below="md">
                        <Flex h="85%" w="full" bgColor="background.base" rounded="lg" overflowY="auto" overflowX="hidden" direction="column">
                            {/*first row*/}
                            <Box h={{lg: "8%", md: "8%", sm: "full", base: "full"}}
                                 w="full"
                                 justifyContent="space-between"
                                 alignItems="center"
                                 display="flex"
                            >
                                <Box>
                                    <PanelEntry
                                        label="Your Library"
                                        icon={<BiLibrary/>}
                                        isActive={panels.activePanel === PanelType.LIBRARY}
                                        onClick={() => togglePanels(PanelType.LIBRARY)}/>
                                </Box>
                                <Box>
                                    <AppIconButton ariaLabel="plus" icon={<LuPlus/>}/>
                                    <AppIconButton ariaLabel="arrow-right" icon={panels.leftPanelExpanded ? <FaArrowLeft/> : <FaArrowRight/>}
                                                   onClick={toggleLeftPanel}/>
                                </Box>
                            </Box>

                            {/* filters */}
                            {/*TODO: add filter logic + state*/}
                            <Box paddingLeft={2} paddingBottom={2}>
                                {filters.map((filter) => (
                                    <FilterBubble filter={filter} key={filter.label}/>
                                ))}
                            </Box>

                            {panels.leftPanelExpanded ? <><Box bgColor="background.hover" w="full" h="2px"/></> : <></>}

                            {/* playlists */}
                            <Flex
                                overflowY="auto"
                                direction="column"
                                align="center"
                                p={2}
                                sx={{
                                    '::-webkit-scrollbar': {
                                        display: 'none'
                                    }
                                }}>
                                {
                                    isLoading ?
                                        <Spinner size="xl" color="primary.base"/>
                                        : playlists?.map((playlist: Playlist) => (
                                            <PlaylistCard playlist={playlist}/>
                                        ))
                                }
                            </Flex>
                        </Flex>
                    </Hide>
                </Box>


                {/*main panel*/}
                <Box h="full" w="full" bgColor="background.base" rounded="lg" overflow="auto">
                    {getActiveView()}
                </Box>
            </Flex>
            {/* play navigation */}
            <Footer mediaController={mediaController} onBack={onBack} onSkip={onSkip}/>
        </Box>
    </>)
}

type PanelEntryProps = {
    label: string,
    icon: ReactElement | undefined,
    isActive: boolean,
    onClick: () => void
}

function PanelEntry({label, icon, isActive, onClick}: PanelEntryProps) {
    return (<>
        <Button
            w="full"
            h="full"
            leftIcon={icon}
            backgroundColor="transparent"
            rounded="lg"
            color={isActive ? "primary.lighter" : "gray"}
            _hover={{color: "background.light", backgroundColor: "transparent"}}
            justifyContent={{lg: "flex-start", md: "flex-start", sm: "center", base: "center"}}
            onClick={onClick}
        >
            {label}
        </Button>
    </>)
}


type FilterBubbleProps = {
    filter: Filter
}

function FilterBubble({filter}: FilterBubbleProps) {
    return (<>
        <Button
            flexShrink={0}
            onClick={filter.onClick}
            backgroundColor={filter.selected ? "primary.base" : "background.accentDark"}
            color={filter.selected ? "background.dark" : "background.light"}
            fontSize="0.8rem"
            margin={1}
            _hover={{backgroundColor: filter.selected ? "primary.lighter" : "background.hover"}}
        >{filter.label}
        </Button>
    </>)
}

type PlaylistCardProps = {
    playlist: Playlist
}

function PlaylistCard({playlist}: PlaylistCardProps) {
    return (<>
        <Flex
            transition="all 150ms linear"
            _hover={{backgroundColor: "background.accentDark"}}
            w="full"
            h="full"
            p={2}
            cursor="pointer"
            alignItems="center"
            gap={2}
            rounded="lg"
            // maxH="100px"
        >
            <Image
                w="60px"
                h="60px"
                fit="cover"
                borderRadius="12px"
                alt="TemplatesKart"
                src="data:image/jpeg;base64,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"
            />
            <Box>
                <Text color="background.light" isTruncated>{playlist.name}</Text>
                <Text color="gray" isTruncated>Playlist • {playlist.user.username} </Text>
            </Box>
        </Flex>
    </>)
}

type ShowcasedPlaylistCardProps = {
    playlist: Playlist,
    onClick?: () => void
}

function ShowcasedPlaylistCard({playlist, onClick}: ShowcasedPlaylistCardProps) {
    return (<>
        <Flex
            transition="all 150ms linear"
            _hover={{backgroundColor: "background.dark100"}}
            h="full"
            p="0 10px 0 2px"
            cursor="pointer"
            alignItems="center"
            gap={3}
            rounded="lg"
            flex="0 0 49%"
            bgColor="background.hover"
            justifyContent="space-between"
        >
            <Flex direction="row" alignItems="center" justifyContent="center" gap={3}>
                <Image
                    w="60px"
                    h="60px"
                    fit="cover"
                    borderRadius="12px"
                    alt="TemplatesKart"
                    src="data:image/jpeg;base64,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"
                />
                <Text color="background.light" isTruncated>{playlist.name}</Text>
            </Flex>

            <AppIconButton ariaLabel="bbb" icon={<RiPlayCircleFill/>} initialScale={1.7} scale={1.9} onClick={onClick}/>
        </Flex>
    </>)
}

type PlaylistSectionProps = {
    label: string,
    playlists?: Playlist[]
}

// TODO: truncate if more than 5 and show "Show More"
function PlaylistSection({label, playlists}: PlaylistSectionProps) {
    return (<>
        <Box mt={10} mb={4} cursor="pointer">
            <Text fontWeight="bolder" fontSize="20px" color="background.light" _hover={{textDecoration: "underline"}} mb={2} mt={6}>{label}</Text>
            <Flex>
                {playlists?.map((playlist) => (
                    <Flex
                        transition="all 150ms linear"
                        bgColor="background.hover"
                        direction="column"
                        align="center"
                        gap={2}
                        p={3}
                        rounded="lg"
                        mr={3}
                        flex="0 0 19%"
                        _hover={{backgroundColor: "background.dark100"}}
                    >
                        <Image
                            h="150px"
                            w="90%"
                            fit="cover"
                            src='https://images.unsplash.com/photo-1555041469-a586c61ea9bc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80'
                            alt='Green double couch with wooden legs'
                            borderRadius='lg'
                        />
                        <Text color="background.light" isTruncated>{playlist.name}</Text>
                        <Text color="gray" textOverflow="ellipsis" noOfLines={2}>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus alias aut
                            beatae dicta
                            dolores earum eos esse est ex illum iusto maxime minima, minus, mollitia pariatur porro quasi sed vero?</Text>
                    </Flex>
                ))}
            </Flex>
        </Box>
    </>)
}